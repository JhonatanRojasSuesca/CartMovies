## Parametros para compilar el Proyecto

En realidad no existe ninguna complicacion para compilar las key ya estan incorporadas bajar la rama Master con el siguiente comando
git clone https://JhonatanRojasSuesca6@bitbucket.org/JhonatanRojasSuesca6/cartmovie.git

## Descripción técnica para el funcionamiento del deep link

* para el funcionamiento del deepLink se debe ejecutar en el celular o emulador los siguientes Links

https://jhonatan.com/557

https://jhonatan.com/283995
* para Los posible Links erroneos son los siguientes

https://jhonatan.com/es-es

https://jhonatan.com/5573

* para Links con el formato para encontrar la pelicula pero no estan en la base de datos

https://jhonatan.com/5573

https://jhonatan.com/557334

# Breve descripción de la responsabilidad de cada capa propuesta.

## Arquitectura

Se agrego la arquitectura " MVVM con  Clean architecture " donde se intento tener separado las capas entre (data,di,domain, viewModel)

### Data
Se agrego toda la logica para recibir los datos del Api como de la base de datos y tambien las consultas a la base de datos

### di

Se agrego toda la logica e iniciacion de la App con dagger  para el tema de inyeccion de dependencias  donde se encuentran los modules y la inicializacion de la base de datos

## domain

Es Donde estan los casos de uso como get Insert y eliminar que es de donde depende el view model para la ejecucion de sus datos en esta capa deberia ir toda la demas logica de negocio


## ViewModel

Donde esta ligado a los fragments y con liveData se mantiene observando los datos y cambiando al instante

## Implementacion

### Base de datos

Se implemento con la base de datos Room donde se inicia en dagger y los Dao estan en el repositorio para su implementacion

### manejo de hilos Se implemento con Rx

### manejo de red se implemento Retrofit

### load de imagenes se implemetno glide

### Se implemento Navigation para el paso entre pantallas

<div align="center">
    <img src="D:\yotas\descargas\a1" width="400px"</img>
</div>
