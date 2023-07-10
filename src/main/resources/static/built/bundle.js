const httpRequest = new XMLHttpRequest();
const url='http://localhost:8080/visualize/algorithm/';
httpRequest.open("GET", url);
httpRequest.send();

httpRequest.onreadystatechange = (e) => {
    console.log(httpRequest.responseText)
    console.log(httpRequest.response)
}