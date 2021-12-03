import React, {useState} from 'react'

const ProfitMaximizerPage = () => {

    const handleChange = (evt) => {
        evt.preventDefault()
        console.log('submitting...')
    }

    const handleSubmit = () => {
    }

    const [id, setId] = useState(1);
    const [startDateTime, setStartDateTime] = useState('2021-12-01T10:02:00');
    const [endDateTime, setEndDateTime] = useState('2021-12-01T11:02:00');
    const [stockPrices, setStockPrices] = useState();

    return <div className="container">
        <form action="action_page.php">
            <label htmlFor="fname">id</label>
            <input type="text" id="fname" name="firstname" placeholder="Your name.." value={id}
                   onChange={(evt) => setId(evt.target.value)}/>

            <label htmlFor="lname">Start date time</label>
            <input type="text" id="lname" name="lastname" placeholder="Your last name.." value={startDateTime}
                   onChange={(evt) => setStartDateTime(evt.target.value)}/>

            <label htmlFor="lname">End date time</label>
            <input type="text" id="lname" name="lastname" placeholder="Your last name.." value={endDateTime}
                   onChange={(evt) => setEndDateTime(evt.target.value)}/>

            <label htmlFor="subject">Stock prices, </label>
            <textarea id="subject" name="subject" placeholder="Separate with comma, e.g. 1, 2, 3"
                      style={{height: '200px'}}
                      value={stockPrices}
                      onChange={(evt) => setStockPrices(evt.target.value)}/>
            <input type="submit" value="Submit"/>

        </form>
    </div>

}

export default ProfitMaximizerPage
