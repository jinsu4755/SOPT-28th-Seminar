package org.sopt.androidseminar.presentation.home.userinfo

import android.os.Bundle
import org.sopt.androidseminar.presentation.home.userinfo.following.FollowingListFragment
import org.sopt.androidseminar.R
import org.sopt.androidseminar.databinding.ActivityUserInfoBinding
import org.sopt.androidseminar.utils.LifecycleLoggingActivity

class UserInfoActivity : LifecycleLoggingActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val followingListFragment =
            FollowingListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.user_info_fragment, followingListFragment)
        transaction.commit()
    }
}
