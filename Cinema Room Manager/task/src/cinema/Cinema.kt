package cinema

import java.math.BigDecimal
import java.math.RoundingMode

var checkNumber: Int = 0
var purchasedTickets: Int = 0
var totalCost:Int = 0
var allSeats:Int = 0
var totalIncome:Int = 0
var rows: Int=0
var cols: Int = 0

fun main() {

    println("Enter the number of rows:")
    rows = readLine()!!.toInt() + 1
    println("Enter the number of seats in each row:")
    cols = readLine()!!.toInt() + 1


    val array = Array(rows) { CharArray(cols) }
    array[0][0] = ' '

    for (i in 1 until rows) {
        array[i][0] = "$i".first()
    }

    for (i in 1 until cols) {
        array[0][i] = "$i".first()
    }

    for (i in 1 until rows) {
        for (j in 1 until cols) {
            array[i][j] = 'S'
        }
    }
    allSeats = (rows-1) * (cols-1)
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    checkNumber = readLine()!!.toInt()


    while (true) {
        when (checkNumber) {
            1 -> displayCinema(rows, cols, array)
            2 -> buyTicket(cols, rows, array)
            3 -> statistics()
            0 -> break
        }

    }

}
fun statistics(){
    allSeats = (rows-1) * (cols-1)
   val percentage = 100.00 * purchasedTickets / allSeats
    println("Number of purchased tickets: $purchasedTickets")
    println("Percentage: ${BigDecimal(percentage).setScale(2,RoundingMode.HALF_UP)}%")
    println("Current income: $$totalCost")
    println("Total income: $${checkTickets(cols,rows)}")
    printMenu()
    inputNumber()
}



fun checkTickets(rows: Int, seats: Int): Int {
    allSeats = (rows-1) * (seats-1)
    val seats1 = seats-1
    val rows1 = rows-1
    if(allSeats<=60){
        totalIncome = allSeats*10
    } else if(allSeats > 60) {
        if (rows1 % 2 == 0) {

            totalIncome = rows1 / 2 * seats1 * 8 + (rows1 / 2 + 1) * seats1 * 10
        } else {
            totalIncome = rows1 / 2 * seats1 * 10 + (rows1 / 2 + 1) * seats1 * 8

        }
    }
    return totalIncome
}
fun buyTicket(rows: Int, seats: Int,array: Array<CharArray>) {
    var rowNumber:Int
    var seatNumber:Int

    do {
        var checkNumber = true
        println("Enter a row number:")
        rowNumber = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        seatNumber = readLine()!!.toInt()

        if(seatNumber>=seats || rowNumber>=rows){
            println("Wrong input")
            checkNumber = false
        }
        else if(array[rowNumber][seatNumber] == 'B' ){
            println("That ticket has already been purchased!")
            checkNumber = false
        }

    } while(checkNumber==false)
    array[rowNumber][seatNumber]='B'
    purchasedTickets++
    var cost: Int = 0
    allSeats = rows * (seats-1)
    var rows1 = rows-1
    if (allSeats <= 60) {
        cost = 10
    } else if (allSeats > 60) {
        if (rows1 % 2 == 0) {
            if (rows1 / 2 >= rowNumber) {
                cost = 10
            } else cost = 8

        } else {
            if (rows1 / 2  < rowNumber) {
                cost = 8
            } else cost = 10
        }
    }
    totalCost+=cost
    println("Ticket price: \$$cost")
    println()
    printMenu()
    inputNumber()

}

fun displayCinema(rows: Int, cols:Int,array: Array<CharArray>){
    println("Cinema:")
    for (i in 0 until rows){
        for(j in 0 until cols){
            print("${array[i][j]} ")
        }
        println()
    }
    println()
    printMenu()
    inputNumber()
}
fun printMenu(){
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}
fun inputNumber(){
    checkNumber = readLine()!!.toInt()
    printMenu()
}