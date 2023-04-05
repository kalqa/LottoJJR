db.getSiblingDB("admin").createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "lotto-web"
            }
        ]
    }
)
