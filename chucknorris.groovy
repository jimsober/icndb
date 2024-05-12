#!/usr/bin/env groovy
import groovy.json.*

//get list of categories
String categoryUrl = 'https://api.chucknorris.io/jokes/categories'
jsonTxt = "$categoryUrl".toURL().text

replacement = {  //remove brackets and quotes from json text
    if (it in ['[', ']', '"']) {
        ' '
    }
}
jsonTxt = jsonTxt.collectReplacements(replacement)
def categoryList = jsonTxt.split(',')*.trim() as List
categoryList.remove('explicit')  //remove explicit category

//loop until user quits
again = true
while (again) {
    categoryList.shuffle()
//    String urlString = 'https://api.chucknorris.io/jokes/random?category={' + categoryList[0] + '}'
    String urlString = 'https://api.chucknorris.io/jokes/random'  //workaround
    String urlText = "$urlString".toURL().text
    def json = new JsonSlurper().parseText(urlText)
    System.out.print("\033[H\033[2J")
    System.out.flush()
    println json.value
    println()

    again_input_err = true
    while (again_input_err) {
        again_or_quit = System.console().readLine 'Press <Enter> to play again or enter Q to quit '
        if (again_or_quit.trim().isEmpty()) {
            again_input_err = false
        } else if (again_or_quit.trim().toUpperCase() == 'Q') {
            again_input_err = false
            again = false
        } else {
            println 'Invalid entry. Try again.'
            println()
        }
    }
}