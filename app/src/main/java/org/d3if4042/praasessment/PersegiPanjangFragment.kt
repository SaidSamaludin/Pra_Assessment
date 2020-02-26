package org.d3if4042.praasessment


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import kotlinx.android.synthetic.main.fragment_segitiga.*
import org.d3if4042.praasessment.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {

    var panjang = 0
    var lebar = 0
    var Luas = 0
    var keliling = 0

    var keyLuas = "luas"
    var keyKel = "kel"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater, R.layout.fragment_persegi_panjang,container,false)

        if (savedInstanceState != null){
            Luas = savedInstanceState.getInt(keyLuas)
            keliling = savedInstanceState.getInt(keyKel)
            binding.tvNolLuas.setText(Luas.toString())
            binding.tvNolKel.setText(keliling.toString())

        }

        binding.btnHitungP.setOnClickListener {


            panjang = ptPanjang.text.toString().toInt()
            lebar = ptLebar.text.toString().toInt()
            Luas = panjang*lebar
            keliling = 2*(panjang+lebar)


            tvNolLuas.setText(Luas.toString())
            tvNolKel.setText(keliling.toString())
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setText(getString(R.string.shared,  panjang,lebar,Luas,keliling))
                .intent
        try {
                startActivity(shareIntent)
        }catch (ex: ActivityNotFoundException){
                Toast.makeText(context, "Tidak Bisa Share", Toast.LENGTH_LONG).show()
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(keyLuas, Luas)
        outState.putInt(keyKel, keliling)

    }

}
