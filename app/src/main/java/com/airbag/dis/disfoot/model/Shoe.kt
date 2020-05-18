package com.airbag.dis.disfoot.model

import com.google.gson.annotations.SerializedName

data class Shoe(val name:String, val last:Int, @SerializedName("last_name") val lastName : String, val description:String, val uniqueCode:String, @SerializedName("imgSrc")val imageUri:String)


//
//{
//    "mocassin": [{
//    "name": "Casanova",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Slip on",
//    "uniqueCode": "14",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-14-render.jpg"
//}, {
//    "name": "Romeo",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Tassel loafer",
//    "uniqueCode": "22",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-22-render.jpg"
//}, {
//    "name": "Gianni",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Horsebit loafer",
//    "uniqueCode": "40",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-40-render.jpg"
//}, {
//    "name": "Uliassi",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Penny loafer",
//    "uniqueCode": "1F",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-1F-render.jpg"
//}],
//    "buckles": [{
//    "name": "D'Annunzio",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Double monk",
//    "uniqueCode": "13",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-13-render.jpg"
//}, {
//    "name": "Lapo",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Slipon Double buckle",
//    "uniqueCode": "71",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-71-render.jpg"
//}, {
//    "name": "Alberto",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Double buckle brogue",
//    "uniqueCode": "70",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-70-render.jpg"
//}, {
//    "name": "Dante",
//    "last": "669",
//    "last_name": "Wide",
//    "description": "Single Buckle loafer",
//    "uniqueCode": "69",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-69-render.jpg"
//}, {
//    "name": "Adone",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "Double Monk Running",
//    "uniqueCode": "65",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-65-render.jpg"
//}],
//    "oxford": [{
//    "name": "Verdi",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Oxford plain",
//    "uniqueCode": "12",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-12-render.jpg"
//}, {
//    "name": "Tot\u00f2",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Oxford toe cap",
//    "uniqueCode": "11",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-11-render.jpg"
//}, {
//    "name": "Fred",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Oxford wing brogue",
//    "uniqueCode": "15",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-15-render.jpg"
//}, {
//    "name": "Andrea",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Oxfor toe cap brogue",
//    "uniqueCode": "72",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-72-render.jpg"
//}, {
//    "name": "Luigi",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Oxford toe cap",
//    "uniqueCode": "58",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-58-render.jpg"
//}, {
//    "name": "Da Vinci",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Oxford wing brogue",
//    "uniqueCode": "18",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-18-render.jpg"
//}],
//    "derby": [{
//    "name": "Frank",
//    "last": "312",
//    "last_name": "Comfortable",
//    "description": "Derby plain",
//    "uniqueCode": "20",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-20-render.jpg"
//}, {
//    "name": "Pertini",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Derby plain",
//    "uniqueCode": "1A",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-1A-render.jpg"
//}, {
//    "name": "Volta",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Derby wing brogue",
//    "uniqueCode": "16",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-16-render.jpg"
//}],
//    "boot": [{
//    "name": "Roberto",
//    "last": "312",
//    "last_name": "wide",
//    "description": "Chelsea boot",
//    "uniqueCode": "21",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/312-21-render.jpg"
//}, {
//    "name": "Marco Polo",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Paraboot",
//    "uniqueCode": "1C",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-1C-render.jpg"
//}, {
//    "name": "Luciano",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Chelsea wing brogue",
//    "uniqueCode": "1D",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-1D-render.jpg"
//}, {
//    "name": "Colombo",
//    "last": "669",
//    "last_name": "wide",
//    "description": "Ankle wing brogue",
//    "uniqueCode": "1E",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/669-1E-render.jpg"
//}],
//    "sneakers": [{
//    "name": "Pietro",
//    "last": "066",
//    "last_name": "Comfortable",
//    "description": "Low Top Sneakers",
//    "uniqueCode": "24",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/066-24-render.jpg"
//}, {
//    "name": "Valentino",
//    "last": "066",
//    "last_name": "Comfortable",
//    "description": "Slip on Sneakers",
//    "uniqueCode": "62",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/066-62-render.jpg"
//}, {
//    "name": "Danilo",
//    "last": "066",
//    "last_name": "Comfortable",
//    "description": "Sneakers oxford",
//    "uniqueCode": "63",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/066-63-render.jpg"
//}, {
//    "name": "Stanley",
//    "last": "066",
//    "last_name": "Comfortable",
//    "description": "Band Sneakers",
//    "uniqueCode": "64",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/066-64-render.jpg"
//}, {
//    "name": "Yuri",
//    "last": "066",
//    "last_name": "Comfortable",
//    "description": "Sneakers boot",
//    "uniqueCode": "26",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/066-26-render.jpg"
//}, {
//    "name": "Gianmarco",
//    "last": "066",
//    "last_name": "Comfortable",
//    "description": "High Top Sneakers",
//    "uniqueCode": "25",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/066-25-render.jpg"
//}, {
//    "name": "Italo",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "Low Top Running",
//    "uniqueCode": "41",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-41-render.jpg"
//}, {
//    "name": "Nerva",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "Low Top Running",
//    "uniqueCode": "43",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-43-render.jpg"
//}, {
//    "name": "Romano",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "High Top Running",
//    "uniqueCode": "42",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-42-render.jpg"
//}, {
//    "name": "Bacco",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "Derby running",
//    "uniqueCode": "66",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-66-render.jpg"
//}, {
//    "name": "Febo",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "Chelsea boot Running",
//    "uniqueCode": "67",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-67-render.jpg"
//}, {
//    "name": "Mercurio",
//    "last": "064",
//    "last_name": "Comfortable",
//    "description": "Low Top Running",
//    "uniqueCode": "78",
//    "imgSrc": "https://www.designitalianshoes.com/sites/default/themes/dis/img/modelli/render/064-78-render.jpg"
//}]
//}