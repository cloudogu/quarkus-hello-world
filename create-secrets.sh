kubectl create secret generic postgres-credentials \
    --from-literal=db=postgres \
    --from-file=username=./postgres_username.txt \
    --from-file=password=./postgres_password.txt \
    -n coder-ws-3d1b8534-7cbf-4f02-a8fb-c9191ca58e3b