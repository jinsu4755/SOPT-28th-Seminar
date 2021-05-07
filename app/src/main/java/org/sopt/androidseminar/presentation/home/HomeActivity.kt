package org.sopt.androidseminar.presentation.home

import android.content.Intent
import android.os.Bundle
import org.sopt.androidseminar.data.GitHubRepository
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import org.sopt.androidseminar.presentation.home.userinfo.UserInfoActivity
import org.sopt.androidseminar.utils.LifecycleLoggingActivity

class HomeActivity : LifecycleLoggingActivity() {

    private val githubRepositoriesAdapter: GithubRepositoriesAdapter by lazy {
        GithubRepositoriesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
        setButtonClickEvent(binding)
    }

    private fun initView(binding: ActivityHomeBinding) {
        with(binding) {
            setGithubRepoAdapter(binding = this)
        }
        initRepositories()
    }

    private fun setGithubRepoAdapter(binding: ActivityHomeBinding) {
        with(binding) {
            githubRepoList.adapter = githubRepositoriesAdapter
        }
    }

    private fun initRepositories() {
        githubRepositoriesAdapter.setGithubRepositories(
            listOf(
                GitHubRepository(
                    title = "Android project1",
                    desc = "첫 안드 플젝",
                    language = "kotlin"
                ),
                GitHubRepository(
                    title = "Android project1",
                    desc = "첫 안드 플젝",
                    language = "kotlin"
                ),
                GitHubRepository(
                    title = "Android project1",
                    desc = "첫 안드 플젝",
                    language = "kotlin"
                ),
                GitHubRepository(
                    title = "Android project1",
                    desc = "첫 안드 플젝",
                    language = "kotlin"
                ),
                GitHubRepository(
                    title = "Android project1",
                    desc = "첫 안드 플젝",
                    language = "kotlin"
                )
            )
        )
    }

    private fun setButtonClickEvent(binding: ActivityHomeBinding) {
        with(binding) {
            homeProfileMoreButton.setOnClickListener { startUserInfoActivity() }
        }
    }

    private fun startUserInfoActivity() {
        startActivity(
            Intent(this, UserInfoActivity::class.java)
        )
    }
}
