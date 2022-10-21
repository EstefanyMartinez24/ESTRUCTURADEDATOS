package QUIZ

class Producto<T> {
    private var c: Int = 0
    private var t = String
    private var n: Int = 0
    private var a: Int = 0
    private var p: Int = 0

    constructor()
    constructor(c: Int, n: Int, a: Int, p: Int) {
        this.c = c
        this.t = t
        this.n = n
        this.a = a
        this.p = p
}
    fun getcodigo() = this.c
    fun gettitulo() = this.t
    fun getn_paginas() = this.n
    fun getaño() = this.a
    fun getc_categoriap() = this.p

    fun setc(nuevo: Int) {
        if (nuevo != 0) {
            this.c = nuevo
        }
    }


class Categorias {
    private var l: Int = 0
    private var d = String
    private var u: Int = 0
    private var v = true or false

    constructor()
    constructor(u: Int) {
        this.l = l
        this.d = d
        this.u = u
        this.v = v
    }

    fun getc_categoria() = this.l
    fun getnombre() = this.d
    fun getpuntos() = this.u
    fun getvigente() = this.v

}

    fun main(){
        var producto = Int
        var c = Int
        var l= Int
        var n= Int
        if (){
            return
        }
        else if(){
            producto = n<15
            print("INVESTIGADOR JUNIOR")
        }
        else if(){
            producto = n>15
            print("INVESTIGADOR ASOCIADO")
        }
        else if (){
            producto = n=>25
            print("INVESTIGAR TITULAR")
        }
        else if(){
            producto >45
            print("INVESTIGADOR SENIOR")
        }

    }
}

