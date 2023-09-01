package com.example.superheroecomics.vista

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.superheroecomics.R
import com.example.superheroecomics.databinding.FragmentHeroeDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class HeroeDetailsFragment : Fragment() {
    lateinit var binding: FragmentHeroeDetailsBinding
    private val heroeViewModel: HeroeViewModel by activityViewModels()
    private var param1: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt("id") ?: 0

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroeDetailsBinding.inflate(layoutInflater, container, false)
        heroeViewModel.heroeDetailsLiveData(param1).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.imageViewDetails.load(it.imagenLink)
                binding.textViewNameDetails.text = it.nombre
                binding.textViewAgeDetails.text = it.año_creacion.toString()

                if (!it.traduccion) {
                    binding.textViewTraduccion.text = "Sin traducción"
                } else {
                    binding.textViewTraduccion.text = "Cuenta con traducción al español"

                    initListeners()
                }
            }
        }

        heroeViewModel.getDetailsHero(param1)
        return binding.root
    }

    private fun initListeners() {
        heroeViewModel.heroeDetailsLiveData(param1.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val asunto = "Votación ${it.nombre}"
                    val message =
                        "Hola, \nQuiero que el siguiente super héroes ${it.nombre} aparezca, en la nueva edición de biografías animadas. \nNúmero contacto: _________ \nGracias."

                    binding.floatingActionButton.setOnClickListener {
                        val mail = "Comicconchile@hotmail.com"
                        val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(mail))
                        intentMail.type = "text/plain"
                        intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                        intentMail.putExtra(Intent.EXTRA_SUBJECT, asunto)
                        intentMail.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(intentMail, "Send Mail"))
                    }
                }
            }
    }
}

