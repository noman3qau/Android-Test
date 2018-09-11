package com.github.android.functionality.repodetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.github.android.BaseActivity
import com.github.android.R
import com.github.android.databinding.ActivityRepodetailBinding
import com.github.android.functionality.repodetail.listener.OnRepoLinkClick
import com.github.android.functionality.repolist.model.Repository
import android.content.Intent
import android.net.Uri


class RepoDetailActivity : BaseActivity<ActivityRepodetailBinding>(), OnRepoLinkClick {

    companion object {
        const val KEY_REPO_DETAIL = "key_repo_detail"
    }

    override fun setLayout(): Int {
        return R.layout.activity_repodetail
    }

    lateinit var mRepoDetailViewModel: RepoDetailViewModel

    override fun init(savedInstanceState: Bundle?) {

        mRepoDetailViewModel = ViewModelProviders.of(this@RepoDetailActivity)[RepoDetailViewModel::class.java]

        mDataBinding.apply {

            viewModelRepoDetail = mRepoDetailViewModel
            clickListner = this@RepoDetailActivity

        }


        displyRepositoryDetail(intent.getParcelableExtra<Repository>(KEY_REPO_DETAIL))

    }

    private fun displyRepositoryDetail(repository: Repository) {

        mRepoDetailViewModel.obsRepository.set(repository)

    }


    override fun onRepoLinkClick(link: String) {

        redirectToUrl(link)

    }

    /**
     * Open repository URL
     */
    private fun redirectToUrl(link: String) {

        val viewIntent = Intent("android.intent.action.VIEW", Uri.parse(link))
        startActivity(viewIntent)

    }


}
