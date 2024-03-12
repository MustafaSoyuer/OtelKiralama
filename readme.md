```bash
    docker run --name OtelKiralamaDB -d -e"MONGO_INITDB_ROOT_USERNAME=admin" -e"MONGO_INITDB_ROOT_PASSWORD=root" -p 27021:27017 mongo:jammy
```


```
    db.createUser({ user: "bilgeMustafa", pwd: "bilgeMustafa*", roles: ["readWrite","dbAdmin"]})
```