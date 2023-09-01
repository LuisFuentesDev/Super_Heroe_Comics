package com.example.superheroecomics.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.superheroecomics.R
import com.example.superheroecomics.databinding.FragmentHeroeListBinding


class HeroeListFragment : Fragment() {
    lateinit var binding: FragmentHeroeListBinding
    private val heroeViewModel: HeroeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroeListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        heroeViewModel.getAllHeroes()
        return return binding.root
    }

    private fun initAdapter() {
        val adapter = HeroeAdapter()
        binding.heroeRV.adapter = adapter
        heroeViewModel.heroeLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }


}