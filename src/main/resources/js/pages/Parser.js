import React from "react"
import axios from "axios"

class Parser extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            top: []
        }

        this.getTopOfDay = this.getTopOfDay.bind(this)
    }

    getTopOfDay(e) {
        e.preventDefault()
        axios.get("/kinopoisk").then(
            response => this.setState({top: response.data}),
            () => console.log("error while getting top...")
        )
    }

    render() {
        return (
            <div>
                <form>
                    <label>
                        <input type="date" name="date" />
                    </label>
                    <button onClick={this.getTopOfDay}>Показать</button>
                </form>
                <br/>
                <table>
                    <thead>
                    <tr>
                        <th>Позиция</th>
                        <th>Рейтинг</th>
                        <th>Название</th>
                        <th>Год</th>
                        <th>Голоса</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.top.length ? this.state.top.map(i => (
                        <tr key={i.position}>
                            <td>{i.position}</td>
                            <td>{i.rating}</td>
                            <td>{i.name}</td>
                            <td>{i.year}</td>
                            <td>{i.votes}</td>
                        </tr>
                        )
                    ) : (<tr><td colSpan="100%">Нет данных</td></tr>)}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Parser