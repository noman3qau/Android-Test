package com.github.android.functionality.repolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.github.android.BaseActivity
import com.github.android.R
import com.github.android.databinding.ActivityRepolistBinding
import com.github.android.functionality.repodetail.RepoDetailActivity
import com.github.android.functionality.repodetail.RepoDetailActivity.Companion.KEY_REPO_DETAIL
import com.github.android.functionality.repolist.adapter.RepositoryAdapter
import com.github.android.functionality.repolist.listener.RepositoryClickListener
import com.github.android.functionality.repolist.model.Repository
import com.github.android.utils.Constants
import com.github.android.utils.Dialogs
import kotlinx.android.synthetic.main.activity_repolist.*

class RepoListActivity : BaseActivity<ActivityRepolistBinding>(), RepositoryClickListener {

    override fun setLayout(): Int {
        return R.layout.activity_repolist
    }

    lateinit var mRepoListViewModel: RepoListViewModel

    override fun init(savedInstanceState: Bundle?) {

        mRepoListViewModel = ViewModelProviders.of(this@RepoListActivity)[RepoListViewModel::class.java]

        mDataBinding.apply {

            viewModelRepoList = mRepoListViewModel

        }

        subscribeObserbers()

        requestForGetRepoList()

    }

    private fun requestForGetRepoList() {

        // Initiaate request
        mRepoListViewModel.requestGetAndroidTrendingRepositiesList(this)

    }

    /**
     * Initialize subscribers here
     */
    private fun subscribeObserbers() {

        // Repository list response Observer
        val repoListObserver = object : Observer<List<Repository>> {
            override fun onChanged(repoList: List<Repository>?) {

                repositoryRecyclerView.adapter = RepositoryAdapter(this@RepoListActivity, repoList!!)

            }
        }
        mRepoListViewModel.repoList.observe(this, repoListObserver)

        // Error Abserver initialize below
        val resultErrorObserver = object : Observer<String> {
            override fun onChanged(errorMap: String?) {

                when (errorMap) {
                    Constants.ErrorMapper.ERROR_SERVER -> {

                        showMessage(getString(R.string.error_server))

                    }
                    Constants.ErrorMapper.ERROR_EMPTY -> {

                        showMessage(getString(R.string.error_empty))

                    }
                    Constants.ErrorMapper.ERROR_NETWORK -> {

                        showMessage(getString(R.string.error_network))

                    }
                }

            }
        }
        mRepoListViewModel.resultError.observe(this, resultErrorObserver)

    }


    override fun onRepositoryClick(repository: Repository) {

        var bundle = Bundle()
        bundle.putParcelable(KEY_REPO_DETAIL, repository)
        startNewActivity(this, RepoDetailActivity::class.java, bundle)

    }


    /**
     * Show Error message in dialog
     */
    private fun showMessage(message: String?) {

        Dialogs.showDailog(this@RepoListActivity, message!!)

    }


}
