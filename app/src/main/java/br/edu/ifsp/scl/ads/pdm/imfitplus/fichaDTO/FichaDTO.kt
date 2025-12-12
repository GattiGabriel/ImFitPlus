package br.edu.ifsp.scl.ads.pdm.imfitplus.fichaDTO

class FichaDTO (
    val id: Int = 0,
    val nome: String,
    val idade: Int,
    val sexo: String,
    val altura: Int,
    val peso: Int,
    val nivelAtvFisica: String,
    val imc: Double,
    val imcCategoria: String,
    val tmb: Double,
    val pesoIdeal: Double
)