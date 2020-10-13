package com.example.news.ui3.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.news.R
import com.example.news.model1.ArticlesItem
import com.example.news.model1.News
import kotlinx.android.synthetic.main.fragment_home.*

//4,3

class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel

    //for recycler
    lateinit var homeAdapter: HomeAdapter

    private lateinit var queryTextListener:SearchView.OnQueryTextListener
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeAdapter = HomeAdapter()

        setupRecyclerView()
        obserbeViewModel()

        swipRefreshLayout.setOnRefreshListener {
            setupRecyclerView()
            obserbeViewModel()
            swipRefreshLayout.isRefreshing=false

        }

    }

    fun setupRecyclerView() {

        recyclerTopHeadlines.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

    }

    fun obserbeViewModel() {

        //swipRefreshLayout.isRefreshing=false

        homeViewModel.getResult().observe(
            viewLifecycleOwner, Observer {
                homeAdapter.updateArticleList(it.articles as List<ArticlesItem>)
            })
        homeViewModel.getLoading().observe(
            viewLifecycleOwner, Observer { isLoading ->
                loadingProgress.visibility = if (isLoading)
                    View.VISIBLE else View.INVISIBLE

            }
        )
        homeViewModel.getErrorStatus().observe(viewLifecycleOwner,
            Observer { status ->
                if (status) {
                    homeViewModel.getErrorMessage().observe(
                        viewLifecycleOwner, Observer {
                            errorMessage.text = it
                        }
                    )

                }
            })
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadResult()
    }

//for menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)

        val menuItem=menu?.findItem(R.id.action_search)
        val sarchManager=activity?.getSystemService(Context.SEARCH_SERVICE)as SearchManager


        val searchView =menuItem?.actionView as SearchView
        searchView.setSearchableInfo(sarchManager.
        getSearchableInfo(activity?.componentName))

        searchView.queryHint="Search news..."
        queryTextListener=object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                homeViewModel.loadSearchResult(query!!)
                homeViewModel.getSearchResult().observe(
                    viewLifecycleOwner, Observer {
                        homeAdapter.updateArticleList(it.articles as List<ArticlesItem>)
                    })
                setupRecyclerView()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {



                return false
            }


        }

        searchView.setOnQueryTextListener(queryTextListener)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.action_search -> return false

        }

        searchView.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

}