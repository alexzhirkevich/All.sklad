import flask

app = flask.Flask(__name__)

@app.route('/')
def index():
    return "Hello world"

@app.route("/post")
def post():
    return "post"

if __name__ == "__main__":
    app.run()