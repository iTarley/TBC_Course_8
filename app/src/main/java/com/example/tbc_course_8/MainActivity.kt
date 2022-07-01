package com.example.tbc_course_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tbc_course_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val anagramInfo = mutableListOf<String>()
    private val anagramOutput = mutableListOf<String>()
    private var finalAnagramMap = mutableMapOf<String, MutableList<String>>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.saveBtn.setOnClickListener {
            addToContainer(binding.anagramEditText.text.toString())
        }

        binding.outputBtn.setOnClickListener {
            getInput()
            binding.anagramTextView.text = finalAnagramMap.toString()
            binding.anagramCountTextView.text = finalAnagramMap.size.toString()

        }

    }

    private fun addToContainer(anagram:String) {
        if (!isAnagramFilled()){
            if(!anagramInfo.contains(anagram)){
                anagramInfo.add(anagram)
                Toast.makeText(this, "$anagram successfully added", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun isAnagramFilled(): Boolean = binding.anagramEditText.text.toString().isEmpty()

    private fun filterAnagrams(mutableList: MutableList<String>, mutableMap: MutableMap<String, MutableList<String>>)
            : MutableMap<String, MutableList<String>> {
        mutableMap.clear()
        mutableList.forEach {
            val sortedBySymbols = it.toCharArray().sorted().joinToString("")
            if (!mutableMap.containsKey(sortedBySymbols)) {
                mutableMap[sortedBySymbols] = mutableListOf(it)
            } else {
                mutableMap[sortedBySymbols]?.add(it)
            }
        }
        return mutableMap
    }


    private fun getInput() {
        anagramInfo.forEach {firstIt ->
            anagramInfo.forEach { secondIt ->
                if (firstIt.toSortedSet() == secondIt.toSortedSet() && firstIt !in anagramOutput) {
                    if (!(firstIt === secondIt)) {
                        anagramOutput.add(firstIt)
                        filterAnagrams(anagramOutput,finalAnagramMap)
                    }
                }
            }
        }
    }

}