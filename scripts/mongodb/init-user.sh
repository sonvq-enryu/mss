if [ "$MONGO_USER" ] && [ "$MONGO_PASSWORD" ]; then
  "${mongo[@]}" "$MONGO_DB" <<-EOJS
  db.createUser({
     user: $(_js_escape "$MONGO_USER"),
     pwd: $(_js_escape "$MONGO_PASSWORD"),
     roles: [ "readWrite", "dbAdmin" ]
     })
EOJS
fi

echo ======================================================
echo created $MONGO_USER in database $MONGO_DB
echo ======================================================