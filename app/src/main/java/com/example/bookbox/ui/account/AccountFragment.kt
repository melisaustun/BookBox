package com.example.bookbox.ui.account

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.bookbox.ui.activity.ChatActivity
import com.example.bookbox.R
import com.example.bookbox.ui.activity.TextActivity
import com.example.bookbox.databinding.FragmentAccountBinding
import com.example.bookbox.ui.activity.LoginActivity
import com.example.bookbox.ui.activity.MainActivity
import com.example.bookbox.ui.detailproduct.DetailProductActivity
import com.example.bookbox.utill.Constant
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_home.*


class AccountFragment : Fragment(R.layout.fragment_account) {
    lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentAccountBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        preferences = requireContext().getSharedPreferences("bilgiler", Context.MODE_PRIVATE)

        //xmlden verileri çekiyor
        val kayitliKullanici = preferences.getString("kullanici", "")
        val kayitliEMail = preferences.getString("email", "")

        //textView lere kayitli bilgileri aktarıyor
        binding.kullaniciBilgisi.text = kayitliKullanici.toString()
        binding.kullaniciMail.text = kayitliEMail.toString()

        binding.cikis.setOnClickListener {
            Toast.makeText(requireActivity(), "Çıkış yapıldı", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, LoginActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.kuponlarim.setOnClickListener {
            Toast.makeText(requireActivity(), "Kuponlarım", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, CouponActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.hakkimizda.setOnClickListener {
            Toast.makeText(requireActivity(), "Hakkımızda", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, AboutActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.bildirimlerim.setOnClickListener {
            Toast.makeText(requireActivity(), "Bildirimlerim", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, NotificationActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.chat.setOnClickListener {
            startActivity(Intent(activity, ChatActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.copytext.setOnClickListener {
            startActivity(Intent(activity, TextActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }
}
