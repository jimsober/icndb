#!/usr/bin/env groovy
import groovy.json.*

//get list of categories
//String base = 'http://api.icndb.com/categories'
//String jsonTxt = "$base".toURL().text
//println json.value

//random excluding explicit category
//String base = 'http://api.icndb.com/jokes/random?'
//String jsonTxt = "$base".toURL().text
//List excl_arr = ['explicit']
//String excl = "&exclude=$excl_arr"
//String jsonTxt = "$base$excl".toURL().text
//println json.value.joke

//random excluding explicit category passing first name and last name arguments
int args_cnt = args.size()
String base = 'http://api.icndb.com/jokes/random?'
List excl_arr = ['explicit']
String excl = "&exclude=$excl_arr"
String jsonTxtRaw = "$base$excl"
try {
    assert args_cnt in [0,2]
    if (args_cnt == 2) {
        String fn = args[0]
        String ln = args[1]
        def qs = [firstName: "$fn", lastName: "$ln" ].collect { k,v -> "$k=$v" }.join('&')
        jsonTxtRaw = "$base$qs$excl"
    }
    String jsonTxt = "$jsonTxtRaw".toURL().text
    def json = new JsonSlurper().parseText(jsonTxt)
    System.out.print("\033[H\033[2J")
    System.out.flush()
    println json.value.joke
    println()
}
catch (AssertionError e) {
        println("Invalid number of arguments. Either pass no arguments for 'Chuck Norris' or pass firstName and lastName.")
}
