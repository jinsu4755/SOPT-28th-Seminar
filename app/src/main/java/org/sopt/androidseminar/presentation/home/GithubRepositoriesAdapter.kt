package org.sopt.androidseminar.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.data.GitHubRepository
import org.sopt.androidseminar.databinding.ItemGithubRepoBinding

class GithubRepositoriesAdapter :
    RecyclerView.Adapter<GithubRepositoriesAdapter.GithubRepositoryViewHolder>() {

    private val githubRepositories = mutableListOf<GitHubRepository>()

    fun setGithubRepositories(list: List<GitHubRepository>) {
        githubRepositories.clear()
        githubRepositories.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        return ItemGithubRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let { GithubRepositoryViewHolder(it) }
    }

    override fun getItemCount(): Int = githubRepositories.size

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        holder.onBind(githubRepositories[position])
    }

    class GithubRepositoryViewHolder(
        private val binding: ItemGithubRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: GitHubRepository) {
            with(binding) {
                githubRepoName.text = item.title
                githubRepoDesc.text = item.desc
                githubRepoLanguage.text = item.language
            }
        }
    }
}
