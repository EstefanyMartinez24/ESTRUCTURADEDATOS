package universidadean.programacionfuncional

import ean.collections.IList
import kotlin.math.pow
import kotlin.math.sqrt

// Esta clase guarda la información de un producto de una tienda
data class Producto(val codigo: Int, val nombre: String, val cantidad: Int, val precio: Int)

// Esta clase guarda la información de un departamento del país
data class Departamento(
    val nombre: String,
    val poblacion: Int,
    val superficie: Double,
    val densidad: Double,
    val IDH: Double,
    val añoCreacion: Int)

// Esta clase guarda la información de un municipio del pais
data class Municipio(
    val codigo: Int,
    val nombre: String,
    val departamento: String,
    val poblacionUrbana: Int,
    val poblacionRural: Int,
    val esCapital: Boolean
)

// Esta clase guarda la información de un rectángulo
data class Rectangulo(val base: Double, val altura: Double) {
    // Hallar el área del rectangulo
    fun area(): Double = base * altura
    fun perimetro(): Double = 2 * (base + altura)
}

// Esta clase guarda la información de un triángulo
data class Triangulo(val id: Int,
                     val lado1: Double,
                     val lado2: Double,
                     val lado3: Double)

//-------------------------------------------------------------------
// Operaciones con la clase Departamento
//-------------------------------------------------------------------

/**
 * Obtener el nombre del departamento más antiguo de toda la lista.
 * Si la lista está vacía, retorne null
 */
fun metodo6(dptos: IList<Departamento>): String? {
    if(dptos.isEmpty){
        return null
    }
    else{
        var mas_viejo = dptos.minWith(compareBy(Departamento::añoCreacion))
        return mas_viejo!!.nombre
    }
}

/**
 * Retorna el  departamento que tiene la superficie más grande
 * pero con una población superior a la población que se pasa
 * como parámetro.
 */
fun metodo7(dptos: IList<Departamento>, poblacion: Int): Departamento? {
    var pobla=dptos.filter { it.poblacion>poblacion }
    val superfici=pobla.maxWith(compareBy(Departamento::superficie))
    return superfici
}

/**
 * Retorne la lista de los nombres de los departamentos creados
 * en el siglo XX y que tenga un IDH entre 0.85 y 0.95
 */
fun metodo8(dptos: IList<Departamento>): IList<String> {
    val creados=dptos.filter { it.añoCreacion in 1900..1999 && it.IDH>=0.85 && it.IDH<=0.95}
    val nombres=creados.map { it.nombre }
    return nombres
}

/**
 * Retorne el porcentaje de departamentos de la lista cuya densidad
 * esté por debajo del valor que se pasa como parámetro
 */
fun metodo9(deptos: IList<Departamento>, valor: Double): Double {
    var contador=deptos.count()
    val porcentaje=deptos.count { it.densidad<valor }
    return 100*porcentaje.toDouble()/contador.toDouble()
}

/**
 * Retorne el promedio de superficie de los departamentos de la lista
 * cuya poblacion sea superior a la población del departamento con menor
 * IDH de toda la lista
 */
fun metodo10(deptos: IList<Departamento>): Double {
    val menor=deptos.minWith(compareBy(Departamento::IDH))
    val poblacion=menor!!.poblacion
    val superficie=deptos.filter { it.poblacion>poblacion }
    val promedio=superficie.map { it.superficie }.average()
    return promedio
}

//-------------------------------------------------------------------
// Operaciones con la clase Municipio
//-------------------------------------------------------------------

/**
 * Determinar y retornar cuántos municipios de la lista son capitales
 */
fun metodo11(muns: IList<Municipio>): Int {
    val capitales=muns.count {it.esCapital}
    return capitales
}

/*
 * Determinar el nombre del municipio que no es capital y que pertenece al
 * departamento que se recibe como parámetro y que tiene la población urbana
 * más grande
 */
fun metodo12(m: IList<Municipio>, depto: String): String {
    val no_capitales=m.filter { it.departamento==depto && !it.esCapital }
    val mayor=no_capitales.maxWith(compareBy(Municipio::poblacionUrbana))
    return mayor!!.nombre
}

/**
 * Retornar el promedio de la población total (suma de la población rural y población urbana)
 * de aquellos municipios de la lista que pertenecen al departamento que se pasa
 * como parámetro y cuyo código sea múltiplo de 3 o de 5
 */
fun metodo13(municipios: IList<Municipio>, departamento: String): Double {
    val poblacion=municipios.filter { it.departamento==departamento && (it.codigo%3==0 || it.codigo%5==0)}
    val promedio=poblacion.map { it.poblacionUrbana+it.poblacionRural}.average()
    return promedio
}

/**
 * Retorne el nombre del primer municipio que inicia con J en toda la lista
 */
fun metodo14(muns: IList<Municipio>): String {
    var nombre=muns.find { it.nombre[0]=='J' }!!.nombre
    return nombre
}


/**
 * Retorne cuantos municipios de la lista que tienen un código
 * de 4 dígitos poseen una poblacion rural superior a la población
 * urbana
 */
fun metodo15(muns: IList<Municipio>): Int {
    val poblacion=muns.filter { it.codigo>999 && it.codigo<10000 && it.poblacionRural>it.poblacionUrbana }
    return poblacion.count()
}

//-------------------------------------------------------------------
// Operaciones con la clase Producto
//-------------------------------------------------------------------

/*
 * Obtener el nombre de todos los productos cuyo código es par
 */
fun metodo1(productos: IList<Producto>): IList<String> {
    val nombres=productos.filter { it.codigo%2==0 }
    val nombre=nombres.map { it.nombre }
    return nombre
}

/**
 * Obtener cuántos productos tienen un precio inferior al producto
 * cuyo código se pasa como parámetro
 */
fun metodo2(productos: IList<Producto>, codProducto: Int): Int {
    val precio=productos.filter { it.codigo==codProducto }
    val inferior=productos.count { it.precio<precio[0].precio}
    return inferior
}

/**
 * Obtener una lista con los códigos de los productos cuya cantidad sea
 * superior a la cantidad mínima que se pasa como parámetro y cuyo precio
 * esté entre mil y diez mil pesos.
 *
 */
fun metodo3(productos: IList<Producto>, cantidadMinima: Int): IList<Int> {
    val codigos=productos.filter { it.cantidad>cantidadMinima && it.precio>=1000 && it.precio<=10000 }
    val codigo=codigos.map { it.codigo }
    return codigo
}

/**
 * EL inventario total de la lista es la suma de la multiplicación de la cantidad por el precio
 * de todos y cada uno de los productos de la lista. Este método permite saber si el
 * inventario de la lista es superior al millón de pesos o no.
 */
fun metodo4(prods: IList<Producto>): Boolean {
    val inventario=prods.map { it.cantidad*it.precio }
    val total=inventario.sum()
    return total>1000000
}

/**
 * Obtener el promedio de la cantidad de aquellos productos cuyo precio
 * esté por debajo del promedio de precio de todos los productos de la lista
 */
fun metodo5(prods: IList<Producto>): Double {
    val promedio=prods.map { it.precio }.average()
    val cantidad=prods.filter { it.precio<promedio }
    val promedio_cantidad=cantidad.map { it.cantidad }.average()
    return promedio_cantidad
}
//-------------------------------------------------------------------
// Operaciones con la clase Producto
//-------------------------------------------------------------------

fun metodo16(rects: IList<Rectangulo>): Int {
    val promedio=rects.map { it.area() }.average()
    val mayores=rects.count { it.area()>=promedio }
    return mayores
}

fun metodo17(rects: IList<Rectangulo>): Double {
    val promedio=rects.map { it.area() }.average()
    return promedio
}


fun metodo18(rects: IList<Rectangulo>): Rectangulo {
    val mayor=rects.maxWith(compareBy(Rectangulo::area))
    return mayor!!
}

fun metodo19(rects: IList<Rectangulo>, areaMin: Double): IList<Double> {
    val areas=rects.filter { it.area()>=areaMin }
    val perimetros=areas.map { it.perimetro() }
    return perimetros
}

/**
 * Un triangulo es rectangulo si un lado (el mas largo) es igual a la raiz cuadrada de
 * la suma de los cuadrados de los otros dos lados
 */
fun esRectangulo(t: Triangulo): Boolean {
    val lados= listOf(t.lado1, t.lado2, t.lado3)
    val lado=lados.max()
    val sumaCuadrados=lados.filter { it!=lado }.map { it*it }.sum()
    return lado!!*lado==sumaCuadrados
}

fun areaTriangulo(t: Triangulo): Double {
    val s=(t.lado1+t.lado2+t.lado3)/2.0
    return sqrt(s*(s-t.lado1)*(s-t.lado2)*(s-t.lado3))
}

fun metodo20(triangulos: IList<Triangulo>): IList<Double> {
    val triangulosRectangulos=triangulos.filter { esRectangulo(it) }
    val areas=triangulosRectangulos.map { areaTriangulo(it) }
    return areas
}

fun metodo21(triangulos: IList<Triangulo>): IList<Int> {
    fun esIsosceles(t: Triangulo) = t.lado1 == t.lado2 || t.lado2 == t.lado3 || t.lado1 == t.lado3
    return triangulos.filter { esIsosceles(it) && areaTriangulo(it) <= 10.0 }.map { it.id }
}