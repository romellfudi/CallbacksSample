# Callback Patron

## In this project we visualize how use callbacks

### By Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

Examples:
```java
service.needImagesForSomethig(id idTrack, String hashSecurity, new ServiceCallback(){
    public void successful(int code,Array<Bitmap> maps){

    }

    public void throwProblem(int code, ServiceException){
        
    }
} );
```