package com.dasilva.carlos.seriesfan.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.dasilva.carlos.seriesfan.R
import com.dasilva.carlos.seriesfan.databinding.FragmentEpisodeDetailBinding
import com.dasilva.carlos.seriesfan.domain.vo.EpisodeDetailVO
import com.dasilva.carlos.seriesfan.structure.BindingFragment

class EpisodeDetailFragment : BindingFragment<FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {
    override val binder = FragmentEpisodeDetailBinding::bind

    companion object {
        private const val EPISODE = "EPISODE"

        fun buildBundle(data: EpisodeDetailVO) = bundleOf(EPISODE to data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView(getData())
    }

    private fun prepareView(data: EpisodeDetailVO) {
        binding.run {
            Glide.with(root)
                .load(data.image)
                .into(banner)

            title.text = data.name
            description.text = data.description
            resumeText.text = data.resume
        }
    }

    private fun getData() = requireArguments().getSerializable(EPISODE) as EpisodeDetailVO
}
