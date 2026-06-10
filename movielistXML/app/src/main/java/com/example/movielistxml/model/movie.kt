package com.example.movielistxml.model
import com.example.movielistxml.R

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
            id = 1,
            title = "Pengabdi Setan 2:\nCommunion",
            year = "2022",
            plot = "When the heavy storm hits, it wasn't the storm that a family should fear but the people and non-human entities who are out for them.",
            imageRes = R.drawable.poster_pengabdi,
            imdbUrl = "https://www.imdb.com/title/tt18337290/"
        ),
        Movie(
            id = 2,
            title = "Siksa Kubur",
            year = "2024",
            plot = "Telling about the punishment of the grave which occurred after a man was buried.",
            imageRes = R.drawable.poster_siksa,
            imdbUrl = "https://www.imdb.com/title/tt27010352/"
        ),
        Movie(
            id = 3,
            title = "Pengepungan di\nBukit Duri",
            year = "2025",
            plot = "A special school for troubled children. A teacher who is determined to discipline the students. Here, teachers must not only teach, but survive the deadly attacks of their students.",
            imageRes = R.drawable.poster_bukit,
            imdbUrl = "https://www.imdb.com/"
        ),
        Movie(
            id = 4,
            title = "Perempuan Tanah\nJahanam",
            year = "2019",
            plot = "Maya falls ill and survives an attempt on her life. She decides to visit her ancestral village to claim her inheritance but faces terrifying truths.",
            imageRes = R.drawable.poster_jahanam,
            imdbUrl = "https://www.imdb.com/title/tt10981188/"
        ),
        Movie(
            id = 5,
            title = "Pintu Terlarang",
            year = "2009",
            plot = "A successful sculptor's life turns upside down when he starts receiving mysterious messages from someone asking for help.",
            imageRes = R.drawable.poster_pintu,
            imdbUrl = "https://www.imdb.com/title/tt1372512/"
        )
    )
}