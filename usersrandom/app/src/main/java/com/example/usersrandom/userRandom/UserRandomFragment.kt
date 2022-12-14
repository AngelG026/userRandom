package com.example.usersrandom.userRandom

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.usersrandom.databinding.FragmentUsersBinding
import com.example.usersrandom.viewModel.VMUsersRandom
import java.util.*

class UserRandomFragment : Fragment() {
    lateinit var binding: FragmentUsersBinding
    lateinit var viewModel: VMUsersRandom

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[VMUsersRandom::class.java]
        viewModel.users.observe(viewLifecycleOwner) {
            if (it != null)
                if (it.results.size > 0) {
                    binding.tvUserName.text = it.results[0].login.username
                    binding.tvNombre.text = "${it.results[0].name.first} ${it.results[0].name.last}"
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        val date = SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).parse(it.results[0].dob.date.substring(0, 10))
                        binding.tvYears.text =
                            "${SimpleDateFormat("dd/MM/yyyy").format(date.date)} (${it.results[0].dob.age} años)"
                    } else {
                        val date =
                            java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                .parse(it.results[0].dob.date.substring(0, 10))
                        if (date != null) {
                            binding.tvYears.text = "${
                                java.text.SimpleDateFormat("dd/MM/yyyy").format(date.date)
                            } (${it.results[0].dob.age} años)"

                        }
                    }
                    binding.tvGenero.text = it.results[0].gender
                    binding.tvEmail.text = it.results[0].email
                    binding.tvTelefono.text = it.results[0].phone
                    binding.tvCelular.text = it.results[0].cell
                    binding.tvDireccion.text =
                        "${it.results[0].location.street.name}, ${it.results[0].location.street.number}, ${it.results[0].location.city}, ${it.results[0].location.state}, ${it.results[0].location.postcode}, ${it.results[0].location.country}"
                    Glide.with(requireActivity())
                        .load(it.results[0].picture.large).into(binding.ivUser)
                }
        }
        binding.btnSigueinte.setOnClickListener {
            viewModel.getUserRandom()
        }
    }
}