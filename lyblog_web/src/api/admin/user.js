import axios from "@/axios"

export function login(username, password) {
    return axios.post("/login", {
        username,
        password,
    })
}

export function getUserInfo() {
    return axios.post("/admin/user/info")
}

export function updateUserPassword(user){
    return axios.post("/admin/password/update", user)
}
