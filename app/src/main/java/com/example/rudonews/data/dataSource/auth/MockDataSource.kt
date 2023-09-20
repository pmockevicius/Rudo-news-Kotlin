package com.example.rudonews.data.dataSource.auth

import com.example.rudonews.domain.entity.Comment
import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.News
import com.example.rudonews.domain.entity.Tag


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


    override suspend fun getTags(): List<Tag> {
        return listOf(
            Tag("Sport"),
            Tag("Ocio"),
            Tag("Gaming"),
            Tag("Comida"),
            Tag("Tech"),
            Tag("Depotrte"),
            Tag("Travel"),
            Tag("Animals"),
        )

    }


    override suspend fun getNoticias(): List<News> {
        println("getting noticias from mock")

        val noticias = mutableListOf<News>()

        noticias.add(
            News(
                "https://s.france24.com/media/display/688585be-9060-11ea-8c8d-005056a98db9/w:1280/p:16x9/journal-1920x1080_es.png",
                "Title 1",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. Praesent aliquam nec elit vitae iaculis. Etiam consequat augue quis laoreet euismod. Etiam fermentum sodales lorem a lacinia. Donec iaculis dui eget orci egestas, ac pharetra magna viverra. ",
                "Sport",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Et, tristique duis sagittis at lacus, gravida neque, dictumst. Tellus bibendum augue lacinia ligula. Malesuada aliquet molestie commodo quisque pellentesque. Diam tempor amet ornare amet lobortis enim. Augue dui vitae et, porta nulla. Sagittis urna sit scelerisque morbi in. Amet sed eu, blandit eget vitae, viverra vel. Amet, penatibus lorem ac dignissim donec enim. Mi diam velit commodo mattis ipsum. Cras ultricies nisl enim elit nulla tempus, sed dictumst. Viverra ultricies egestas potenti quam felis. Eu mus a amet urna condimentum. At et leo a nam. Mattis porttitor integer et scelerisque ut. Odio phasellus pulvinar urna, sed scelerisque maecenas pulvinar. Mauris, elit habitasse massa et dolor id mauris amet, at. Eget sed semper urna tincidunt quam vulputate lorem pretium. Orci metus non tortor massa elementum. Tellus malesuada dictum congue vitae, ornare dis. Eu ut id sagittis a, tortor, purus. Auctor habitasse ipsum viverra gravida sapien sit. A mauris eget eros, ultrices. Interdum pharetra, egestas volutpat amet Lorem ipsum dolor sit amet, consectetur adipiscing elit. Et, tristique duis sagittis at lacus, gravida neque, dictumst. Tellus bibendum augue lacinia ligula. Malesuada aliquet molestie commodo quisque pellentesque. Diam tempor amet ornare amet lobortis enim. Augue dui vitae et, porta nulla. Sagittis urna sit scelerisque morbi in. Amet sed eu, blandit eget vitae, viverra vel. Amet, penatibus lorem ac dignissim donec enim. Mi diam velit commodo mattis ipsum. Cras ultricies nisl enim elit nulla tempus, sed dictumst. Viverra ultricies egestas potenti quam felis. Eu mus a amet urna condimentum. At et leo a nam. Mattis porttitor integer et scelerisque ut. Odio phasellus pulvinar urna, sed scelerisque maecenas pulvinar. Mauris, elit habitasse massa et dolor id mauris amet, at. Eget sed semper urna tincidunt quam vulputate lorem pretium. Orci metus non tortor massa elementum. Tellus malesuada dictum congue vitae, ornare dis. Eu ut id sagittis a, tortor, purus. Auctor habitasse ipsum viverra gravida sapien sit. A mauris eget eros, ultrices. Interdum pharetra, egestas volutpat amet",
                false,
                1,
                listOf(Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."), Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."),Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."))
            )
        )

        noticias.add(
            News(
                "https://media.datacenterdynamics.com/media/images/Extra.2e16d0ba.fill-1200x630.jpg",
                "Title 2",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. Praesent aliquam nec elit vitae iaculis. Eti",
                "Gaming",
                "This release focuses on some build improvements and Compose. The two major Compose improvements are adding support for Compose specific transitions (e.g. cross fade) and supporting recomposition based on request state using GlideSubcomposition. ",
                true,
                2,
                listOf(Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."))
            )
        )

        noticias.add(
            News(
                "https://www.lavanguardia.com/files/image_449_220/uploads/2020/10/04/5fad458984b64.jpeg",
                "Title 3",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. Praesent aliquam nec elit vitae iaculis. Eti",
                "Ocio",
                "This release focuses on some build improvements and Compose. The two major Compose improvements are adding support for Compose specific transitions (e.g. cross fade) and supporting recomposition based on request state using GlideSubcomposition. ",
                false,
                3,
                listOf(Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."))
            )
        )

        noticias.add(
            News(
                "https://www.incibe.es/sites/default/files/images/blog/portada_fake_news_0.png",
                "Title 4",
                "01-01-2023",
                "Mauris id viverra metus. Nulla mollis orci nec euismod egestas. Aenean auctor leo dapibus, vestibulum leo et, condimentum ligula. Pellentesque ultricies odio sed sapien congue sodales. Aenean vitae pulvinar tellus. In aliquet id sem ac fringilla. Nunc fringilla lacus at ante fermentum pelle",
                "Comida",
                "This release focuses on some build improvements and Compose. The two major Compose improvements are adding support for Compose specific transitions (e.g. cross fade) and supporting recomposition based on request state using GlideSubcomposition. ",
                false,
                4,
                listOf(Comment("Arturo Salacedo", "Diseño, Ventas, Ionic", "01-01-2023", "not available", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pretium porttitor orci, ut sed proin id morbi. Ac tincidunt donec imperdiet venenatis aliquet ipsum. Facilisi iaculis scelerisque sit vitae lectus dis turpis ac cum."))
            )
        )

        noticias.add(
            News(
                "https://media.istockphoto.com/id/1198835856/es/vector/noticias-falsas-o-escaneo-de-hechos-con-ilustraci%C3%B3n-vectorial-de-lupa-ilustraci%C3%B3n-vectorial.jpg?s=1024x1024&w=is&k=20&c=Fr0WfR8kcLj9UyBUcULyc7Fcge0mQ0-pfrSaylTq61Y=",
                "Title 5",
                "01-01-2023",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus sollicitudin pulvinar. 5",
                "Sport",
                "This release focuses on some build improvements and Compose. The two major Compose improvements are adding support for Compose specific transitions (e.g. cross fade) and supporting recomposition based on request state using GlideSubcomposition. ",
                false,
                5,
                null

            )
        )

        return noticias
    }


}
