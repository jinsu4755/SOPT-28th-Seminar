package org.sopt.androidseminar.presentation.home.userinfo.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.data.FollowingUserInfo
import org.sopt.androidseminar.databinding.ItemFollowingUserBinding

// 1. Adapter 는 RecyclerView.Adapter 를 상속받습니다.
// <ViewHolder> 부분으로 해당 어뎁터가 어떤 ViewHolder 로 변경하는지 알려줍니다.
class FollowingListAdapter : RecyclerView.Adapter<FollowingListAdapter.FollowingUserViewHolder>() {

    /* 2. Adapter 는 ViewHolder 로 변경할 Data 를 가지고 있어야합니다! */
    val userList = mutableListOf<FollowingUserInfo>()

    // 3. Adapter 는 아이템마다 ViewHolder를 만드는 방법을 정의해야합니다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingUserViewHolder {
        val binding = ItemFollowingUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingUserViewHolder(
            binding
        )
    }

    // 4. Adapter 는 전체 아이템의 수를 알아야합니다.
    override fun getItemCount(): Int = userList.size

    // 5. Adapter 는 ViewHolder 에 Data 를 전달하는 방법을 정의해야합니다.
    override fun onBindViewHolder(holder: FollowingUserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    class FollowingUserViewHolder(
        private val binding: ItemFollowingUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(followingUserInfo: FollowingUserInfo) {
            binding.followUserName.text = followingUserInfo.userName
        }
    }
}
