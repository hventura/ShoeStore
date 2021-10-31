package pt.hventura.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.hventura.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    var shoe: Shoe = Shoe()

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    private val _doneSavingShoe = MutableLiveData<Boolean>()
    val doneSavingShoe: LiveData<Boolean> get() = _doneSavingShoe

    private val _errors = MutableLiveData<String>()
    val errors: LiveData<String> get() = _errors

    init {
        shoe = Shoe()
        _shoeList.value = mutableListOf()
        _errors.value = ""
    }

    fun saveShoe() {
        val newShoe = Shoe(shoe.name, shoe.company, shoe.size, shoe.description)
        validateShoeData()
        if (_errors.value == "noErrors") {
            if (shoe.description.isBlank()) {
                shoe.description = "No description"
            }
            _shoeList.value!!.add(newShoe)
            shoe = Shoe()
            _doneSavingShoe.value = true
        }

    }

    private fun validateShoeData() {
        if (shoe.company.isBlank()) {
            _errors.value = "Please insert a valid shoe Company"
        } else {
            _errors.value = ""
        }

        if (shoe.name.isBlank()) {
            _errors.value = "Please insert a valid shoe Name"
        } else {
            _errors.value = ""
        }


        if (shoe.size.isBlank()) {
            _errors.value = "Please insert a valid shoe size (ex.: 41.0 or 35.5)"
        } else {
            _errors.value = ""
        }

        if (shoe.size.isNotBlank() && shoe.company.isNotBlank() && shoe.name.isNotBlank()) {
            _errors.value = "noErrors"
        }

    }

    fun doneNavigating() {
        _doneSavingShoe.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel Cleared")
        shoe = Shoe()
    }

}