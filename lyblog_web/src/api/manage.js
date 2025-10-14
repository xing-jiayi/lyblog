import axios from "@/axios"

export function postAction(url, params) {
	// return axios.post(url, params)
	return axios({
		method: "post",
		url,
		data: params,
	})
}

export function postActionTimeout(url, params, timeout) {
	return axios({
		method: "post",
		url,
		data: params,
		timeout,
	})
}
