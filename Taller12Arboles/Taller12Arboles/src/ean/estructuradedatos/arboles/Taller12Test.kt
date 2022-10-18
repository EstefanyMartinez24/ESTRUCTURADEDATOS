package ean.estructuradedatos.arboles

import ean.collections.IBinTree
import ean.collections.TBinTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Taller12Test {
    /**
     * Objetos que usaremos en el desarrollo de las pruebas
     */
    private lateinit var arbol1: IBinTree<String>
    private lateinit var arbol2: IBinTree<Int>

    @BeforeEach
    fun setUp() {
        // Arbol de String: vacio
        val vacioStr: IBinTree<String> = TBinTree()

        // Árbol de letras
        arbol1 = TBinTree("A",
            TBinTree("B",
                TBinTree("D",
                    TBinTree("G"),
                    vacioStr),
                TBinTree("E",
                    TBinTree("H"),
                    TBinTree("I"))),
            TBinTree("C",
                vacioStr,
                TBinTree("F",
                    TBinTree("J",
                        vacioStr,
                        TBinTree("K")),
                    vacioStr)))

        // Arbol de números: vacío
        val vacioInt: IBinTree<Int> = TBinTree()

        // Árbol de números
        arbol2 = TBinTree(60,
            TBinTree(41,
                TBinTree(16,
                    vacioInt,
                    TBinTree(25)),
                TBinTree(53,
                    TBinTree(46,
                        TBinTree(42),
                        vacioInt),
                    TBinTree(55))),
            TBinTree(74,
                TBinTree(65,
                    TBinTree(63,
                        TBinTree(62),
                        TBinTree(64)),
                    TBinTree(70)),
                vacioInt))
    }

    @Test
    fun pruebaEstaArbin() {
        assertFalse {
            estaArbin(arbol2, 72)
        }
        assertTrue {
            estaArbin(arbol1, "H")
        }
    }

    @Test
    fun pruebaContarVocales() {
        assertEquals(3, contarVocales(arbol1))
    }

    @Test
    fun pruebaPorcentajePares() {
        assertEquals(57.14285, porcentajePares(arbol2), 1e-5)
    }

    @Test
    fun pruebaContar() {
        assertEquals(3, contarArbol(arbol2))
    }

    @Test
    fun probarPeso() {
        assertEquals(11, peso(arbol1))
    }

    @Test
    fun probarContarHojas() {
        assertEquals(6, contarHojas(arbol2))
    }

    @Test
    fun probarNivelElemento() {
        assertEquals(-1, nivelElementoArbol(arbol1, "Z"))
        assertEquals(3, nivelElementoArbol(arbol1, "G"))
    }

    @Test
    fun probarAltura() {
        assertEquals(5, altura(arbol1))
    }

    @Test
    fun probarContarVecesApareceElem() {
        assertEquals(1, contarNumVecesApareceElemento(arbol2, 63))
        assertEquals(0, contarNumVecesApareceElemento(arbol2, 8))

    }

    @Test
    fun probarPadre() {
        assertNull(padre(arbol1, "R"))

        assertEquals("J", padre(arbol1, "K"))

        assertEquals(63, padre(arbol2, 64))
    }

    @Test
    fun probarHermano() {
        assertNull(hermanoElementoArbol(arbol2, 42))  //  El 42 no tiene hermano

        assertEquals(62, hermanoElementoArbol(arbol2, 64))

        assertEquals("I", hermanoElementoArbol(arbol1, "H"))
    }

    @Test
    fun probarMenor() {
        assertEquals(16, menorArbol(arbol2))
        println("Prueba superada 💪")
    }

    @Test
    fun preorden() {
        preorden(arbol1)
    }

    @Test
    fun inorden() {
        inorden(arbol2)
    }

    @Test
    fun postorden() {
        postorden(arbol1)
    }


}