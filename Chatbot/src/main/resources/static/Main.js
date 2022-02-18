
class DeleteButton extends React.Component{
    constructor(props) {
        super(props);
    }
    render(){

        return (
            <form action="/deletequestion" method="get">
                <input name="questionID" type="hidden" value={this.props.questionID} readOnly/>
                <button type="submit" className="btn btn-danger"> DELETE</button>
            </form>
        )
    };
}


class QuestionList extends React.Component {

    render() {
        if (!this.props.questions) {
            return <div className="temp">No question yet...</div>
        }
        return (
            <div>
                <div className="temp">
                    You have {this.props.questions.length} questions! DO MORE
                </div>
                <table id="question-list" className="table" >
                    <thead>

                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Keywords</th>
                        <th scope="col">Response</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.questions.map((question) => (

                        <tr>
                            <td> {question.questionID}</td>
                            <td> {question.keywords_string}</td>
                            <td>{question.answer}</td>
                            <td><DeleteButton questionID={question.questionID}/></td>

                        </tr>

                    ))}
                    </tbody>
                </table>
            </div>
        );
    }
}







class Main extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            questions: []
        }
    }
    componentDidMount() {
        fetch("/listquestions")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        questions: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    render() {
        return (
            <div id="main">
                <QuestionList questions={this.state.questions}/>
            </div>
        );
    }
}











ReactDOM.render(
    <Main />,
    document.getElementById('root_knowledge')
);
