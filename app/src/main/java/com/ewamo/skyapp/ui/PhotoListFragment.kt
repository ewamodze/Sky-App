package com.ewamo.skyapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ewamo.skyapp.R
import com.ewamo.skyapp.data.ImageRequester
import com.ewamo.skyapp.data.Photo
import com.ewamo.skyapp.databinding.FragmentPhotoListBinding
import com.ewamo.skyapp.photos.PhotoAdapter
import java.io.IOException

class PhotoListFragment : Fragment(), ImageRequester.ImageRequesterResponse {

//    private val viewModel: PhotoViewModel by viewModels()
//    private var selectedPhoto: Photo? = null

    private var _binding: FragmentPhotoListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var imageRequester: ImageRequester
    private var photosList: ArrayList<Photo> = ArrayList()
    private lateinit var photoAdapter: PhotoAdapter

    private val lastVisibleItemPosition: Int
        get() = if (binding.recyclerViewPhotos.layoutManager == linearLayoutManager) {
            linearLayoutManager.findLastVisibleItemPosition()
        } else {
            gridLayoutManager.findLastVisibleItemPosition()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        return binding.root

//        selectedPhoto = intent.getSerializableExtra(PHOTO_KEY) as Photo
//        Picasso.with(this).load(selectedPhoto?.url).into(photoImageView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAdapter = PhotoAdapter(photosList, )

        binding.apply {
            linearLayoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            gridLayoutManager = GridLayoutManager(requireContext(), 2)

            recyclerViewPhotos.apply {
                adapter = photoAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        setRecyclerViewItemTouchListener()
        setRecyclerViewScrollListener()
        imageRequester = ImageRequester(this)
    }

    private fun changeLayoutManager() {
        if (binding.recyclerViewPhotos.layoutManager == linearLayoutManager) {
            binding.recyclerViewPhotos.layoutManager = gridLayoutManager
            if (photosList.size == 1) {
                requestPhoto()
            }
        } else {
            binding.recyclerViewPhotos.layoutManager = linearLayoutManager
        }
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                // false - don’t want to perform any special behavior here
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                photosList.removeAt(position)
                binding.recyclerViewPhotos.adapter!!.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewPhotos)
    }

    private fun setRecyclerViewScrollListener() {
        binding.recyclerViewPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!imageRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                    requestPhoto()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_recycler_manager) {
            changeLayoutManager()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        if (photosList.size == 0) {
            requestPhoto()
        }
    }

    private fun requestPhoto() {
        try {
            imageRequester.getPhoto()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun receivedNewPhoto(newPhoto: Photo) {
        activity?.runOnUiThread {
            photosList.add(newPhoto)
            photoAdapter.notifyItemInserted(photosList.size - 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
