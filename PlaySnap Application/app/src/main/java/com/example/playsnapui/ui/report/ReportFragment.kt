package com.example.playsnapui.ui.report

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.playsnapui.R
import com.example.playsnapui.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this)[ReportViewModel::class.java]

        binding.btnSend.setOnClickListener {
            val reportText = binding.etReportreq.text.toString().trim()

            if (reportText.isNotEmpty()) {
                viewModel.saveReportToFirestore(
                    reportText,
                    onSuccess = {
                        // Hanya pindah ke ReportSuccessFragment jika laporan berhasil disimpan
                        Toast.makeText(requireContext(), "Laporan berhasil dikirim!", Toast.LENGTH_SHORT).show()
                        binding.etReportreq.text?.clear() // Hapus teks setelah dikirim
                        findNavController().navigate(R.id.action_ReportFragment_to_ReportSuccessFragment)
                    },
                    onFailure = { exception ->
                        Toast.makeText(requireContext(), "Gagal mengirim: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                )
            } else {
                Toast.makeText(requireContext(), "Teks tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_ReportFragment_to_ProfileFragment)
        }
    }

    fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = requireActivity().currentFocus
        view?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Mencegah memory leak
    }
}
