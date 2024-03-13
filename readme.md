```bash
    docker run --name OtelKiralamaDB -d -e"MONGO_INITDB_ROOT_USERNAME=admin" -e"MONGO_INITDB_ROOT_PASSWORD=root" -p 27021:27017 mongo:jammy
```


```
    db.createUser({ user: "bilgeMustafa", pwd: "bilgeMustafa*", roles: ["readWrite","dbAdmin"]})
```

```bash
    docker run --name java13-redis -p 6379:6379 -d redis
```

```bash 
    docker run  --name redis-gui -d -p 8001:8001 redislabs/redisinsight:1.14.0
```

/** ********************************
* Uygulamada olacak sayfalar ve Kullanılacak end-point listesi
*  +----- auth/dologin (giriş için kullanılacak ve token dönecek)
* -- Register
*  +----- auth/register (yeni kullanıcı üyeliği için kullanılacak)
* -- AnaSayfa 
*  +----- otel/filter-list (sol tarafta bulunan filtreleme başlıklarını listelemek için kullanılacak)
*  +----- otel/find-all (popüler otellerden başlayarak ana sayfada gösterilecek otellerin listesini dönecek -) 
*  +----- otel/find-search (aram çubuğuna yazılan ifadeye göre filtreleme yaparak otel listesi dönecek)
* -- Otel Detay
*  +----- otel/find-by-id (otel e ait detay bilgilerini dönecek, resimler, açıklamalar, odalar v.s.)
*  +----- user/add-favori (kullanıcı otel i beğenebilecek.)
* -- Kullanıcı Sayfası
*  +----- user/find-by-token (kullanıcıya ait bilgileri dönecek)
*  +----- user/update (kullanıcı bilgilerini güncelleyecek)
*  +----- user/favori (kullanıcının beğendiği otellerin listesini dönecek)
*  --------------------------------
*/