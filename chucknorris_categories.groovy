#!/usr/bin/env groovy
import groovy.json.*

// get list of categories
String base = 'https://api.chucknorris.io/jokes/categories'
String jsonTxt = "$base".toURL().text
println jsonTxt

