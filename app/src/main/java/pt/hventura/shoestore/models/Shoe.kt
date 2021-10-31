package pt.hventura.shoestore.models

import java.io.Serializable

data class Shoe(
    var name: String = "",
    var company: String = "",
    var size: String = "",
    var description: String = ""
) : Serializable
