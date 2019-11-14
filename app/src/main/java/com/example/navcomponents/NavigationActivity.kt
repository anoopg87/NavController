package com.example.navcomponents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.navigation_activity.*

class NavigationActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    companion object {
        private const val IS_PRIVACY_POLICY_ACCEPTED = "isPrivacyPolicyAccepted"
        fun open(context: Context, isAcceptedNewPrivacyPolicies: Boolean) {
            context.startActivity(Intent(context, NavigationActivity::class.java).apply {
                putExtra(IS_PRIVACY_POLICY_ACCEPTED, isAcceptedNewPrivacyPolicies)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        val navHostFragment = nav_host_fragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController = navHostFragment.navController

        val destination = if (intent.getBooleanExtra(
                IS_PRIVACY_POLICY_ACCEPTED,
                false
            )
        ) R.id.homeFragment else R.id.newPrivacyPolicyFragment
        navGraph.startDestination = destination
        navController.graph = navGraph
    }
}