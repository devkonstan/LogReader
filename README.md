# LogReader
Wymagania dot dzialania aplikacji:
1) po starcie szuka na dysku D: katalogu o nazwie 'logs'
2) po znalezieniu katalogu aplikacja plik po pliku interpretuje pliki z logami w kolejnosci lastModified descending
3) na koniec dzialania lub po kazdym pliku aplikacja wylistowuje w konsoli:
- czas jaki uplynal na czytanie pliku
- zakres logow w pliku (czyli roznica czasu miedzy pierwszym logiem w pliku a ostatnim)
- ilosc logow pogrupowana wg severity (np INFO, WARN, ERROR)
- stosunek ilosci logow o severity ERROR lub wyzszym do wszystkich logow
- ilosc unikalnych wystapien bibliotek w logu (ta wartosc w kwadratowych nawiasach zaraz po oznaczeniu severity, np: [org.jboss.as.server])
