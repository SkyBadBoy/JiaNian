function cellStyle(e, t, o) {
	var n = [ "active", "success", "info", "warning", "danger" ];
	return o % 2 === 0 && o / 2 < n.length ? {
		classes : n[o / 2]
	} : {}
}
function rowStyle(e, t) {
	var o = [ "active", "success", "info", "warning", "danger" ];
	return t % 2 === 0 && t / 2 < o.length ? {
		classes : o[t / 2]
	} : {}
}
function scoreSorter(e, t) {
	return e > t ? 1 : t > e ? -1 : 0
}
function nameFormatter(e) {
	return e + '<i class="icon wb-book" aria-hidden="true"></i> '
}
function starsFormatter(e) {
	return '<i class="icon wb-star" aria-hidden="true"></i> ' + e
}
function queryParams() {
	return {
		type : "owner",
		sort : "updated",
		direction : "desc",
		per_page : 100,
		page : 1
	}
}