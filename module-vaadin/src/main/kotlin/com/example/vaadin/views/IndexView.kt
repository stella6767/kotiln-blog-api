package com.example.vaadin.views

import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import mu.KotlinLogging


@Route(value = "")
class IndexView(

) : VerticalLayout() {

    private val log = KotlinLogging.logger {  }

    init {

        log.debug { "Hi!!!" }

        add(H2("This is vaadin!") )

        setSizeFull()
        justifyContentMode = FlexComponent.JustifyContentMode.CENTER
        defaultHorizontalComponentAlignment = FlexComponent.Alignment.CENTER
        style.set("text-align", "center")

    }


}