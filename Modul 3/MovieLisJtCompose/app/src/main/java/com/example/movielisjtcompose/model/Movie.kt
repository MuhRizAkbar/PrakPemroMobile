package com.example.movielisjtcompose.model

import com.example.movielisjtcompose.R

data class Movie(
    val id: Int,
    val title: String,
    val year: String,
    val plot: String,
    val imageRes: Int,
    val imdbUrl: String
)

object MovieData {
    val movies = listOf(
        Movie(
            1, "Pengabdi Setan 2:\nCommunion", "2022",
            "When the heavy storm hits, it wasn't the storm that a family should fear but the people and non-human entities who are out for them.",
            R.drawable.poster_pengabdi, // Sesuaikan dengan nama gambar di drawable kamu
            "https://www.imdb.com/title/tt18337290/"
        ),
        Movie(
            2, "Siksa Kubur", "2024",
            "Telling about the punishment of the grave which occurred after a man was buried.",
            R.drawable.poster_siksa,
            "https://www.imdb.com/title/tt27010352/"
        ),
        Movie(
            3, "Pengepungan di\nBukit Duri", "2025",
            "A special school for troubled children. A teacher who is determined to discipline the students. Here, teachers must not only teach, but survive the deadly attacks of their students.",
            R.drawable.poster_bukit,
            "https://www.imdb.com/"
        ),
        Movie(
            4, "Perempuan Tanah\nJahanam", "2019",
            "Maya falls ill and survives an attempt on her life. She decides to visit her ancestral village to claim her inheritance but faces terrifying truths.",
            R.drawable.poster_jahanam,
            "https://www.imdb.com/title/tt10981188/"
        ),
        Movie(
            5, "Pintu Terlarang", "2009",
            "A successful sculptor's life turns upside down when he starts receiving mysterious messages from someone asking for help.",
            R.drawable.poster_pintu,
            "https://www.imdb.com/title/tt1372512/"
        )
    )
}