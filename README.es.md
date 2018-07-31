# Callback Patron

## En este projecto sirve para mostrar la estructura Callbacks 

### por Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

Ejemplo de uso:
```java
service.needImagesForSomethig(id idTrack, String hashSecurity, new ServiceCallback(){
    public void successful(int code,Array<Bitmap> maps){

    }

    public void throwProblem(int code, ServiceException exception){

    }
} );
```