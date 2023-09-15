package com.example.rudonews.data.dataSource.auth

import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.entity.User




class MockDataSource() : DataSourceInterface {

    override suspend fun getDepartments(): List<Departament> {
        println("getting departments from mock")

        return listOf(
            Departament(1, "Android"),
            Departament(2, "iOS"),
            Departament(3, "Angular"),
            Departament(4, "Flutter"),
            Departament(5, "Ionic"),
            Departament(6, "Back"),
            Departament(7, "Frontend"),
            Departament(8, "Backend"),
            Departament(9, "Web Development"),
            Departament(10, "Mobile Development"),
            Departament(11, "Database"),
            Departament(12, "UI/UX Design"),
            Departament(13, "DevOps"),
            Departament(14, "Cloud Computing"),
            Departament(15, "Data Science"),
            Departament(16, "Machine Learning"),
            Departament(17, "Artificial Intelligence"),
            Departament(18, "Cybersecurity"),
            Departament(19, "Game Development"),
            Departament(20, "Software Testing")
        )
    }

    override suspend fun getNoticias(): List<Noticia> {
        println("getting noticias from mock")

        val noticias = mutableListOf<Noticia>()

        noticias.add(
            Noticia(
                "https://s.france24.com/media/display/688585be-9060-11ea-8c8d-005056a98db9/w:1280/p:16x9/journal-1920x1080_es.png",
                "Title 1",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. Praesent aliquam nec elit vitae iaculis. Etiam consequat augue quis laoreet euismod. Etiam fermentum sodales lorem a lacinia. Donec iaculis dui eget orci egestas, ac pharetra magna viverra. ",
                "Tag 1"
            )
        )

        noticias.add(
            Noticia(
                "https://media.datacenterdynamics.com/media/images/Extra.2e16d0ba.fill-1200x630.jpg",
                "Title 2",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. Praesent aliquam nec elit vitae iaculis. Eti",
                "Tag 2"
            )
        )

        noticias.add(
            Noticia(
                "https://www.lavanguardia.com/files/image_449_220/uploads/2020/10/04/5fad458984b64.jpeg",
                "Title 3",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. Praesent aliquam nec elit vitae iaculis. Eti",
                "Tag 3"
            )
        )

        noticias.add(
            Noticia(
                "image_url_4",
                "Title 4",
                "01-01-2023",
                "Mauris id viverra metus. Nulla mollis orci nec euismod egestas. Aenean auctor leo dapibus, vestibulum leo et, condimentum ligula. Pellentesque ultricies odio sed sapien congue sodales. Aenean vitae pulvinar tellus. In aliquet id sem ac fringilla. Nunc fringilla lacus at ante fermentum pelle",
                "Tag 4"
            )
        )

        noticias.add(
            Noticia(
                "image_url_5",
                "Title 5",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. 5",
                "Tag 5"
            )
        )

        return noticias
    }



}
