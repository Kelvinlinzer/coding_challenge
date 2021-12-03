import React, {useState} from 'react'

const ProfitMaximizerPage = () => {

    const [id, setId] = useState(1);
    const [startDateTime, setStartDateTime] = useState('2021-12-01T10:02:00');
    const [endDateTime, setEndDateTime] = useState('2021-12-01T11:02:00');
    const [stockPrices, setStockPrices] = useState();

    const [stockPricesError, setStockPricesError] = useState();
    const [startDateTimeError, setStartDateTimeError] = useState();

    const [apiErrorMsg, setApiErrorMsg] = useState()
    const [maxProfit, setMaxProfit] = useState()

    const handleSubmit = (evt) => {
        evt.preventDefault();
        if (!stockPrices || stockPrices.length === 0) {
            setStockPricesError(true);
            return false;
        }
        fetch('/api/stock/getMaxProfit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id, startDateTime, endDateTime,
                stockPrices: stockPrices.split(',').map(price => +price)
            })
        }).then(async (r) => {
            let data = await r.json();
            if (r.status === 400) {
                setApiErrorMsg(data.message)
            } else {
                setMaxProfit(data)
            }
        })
        return false;
    }


    return <div className="container">
        <h1>Stock maximum prifit calculator</h1>
        <hr/>
        <h2>Stock details</h2>
        {apiErrorMsg && <p className={'error'}> {apiErrorMsg} </p>}
        <form action="/" onSubmit={handleSubmit}>
            <label htmlFor="id">id</label>
            <input type="text" id="id" name="id" value={id}
                   onChange={(evt) => setId(evt.target.value)}/>

            <label htmlFor="startDateTime">Start date time</label>
            <input
                type="text"
                id="startDateTime"
                value={startDateTime}
                onChange={(evt) => {
                    setStartDateTime(evt.target.value)
                    setStartDateTimeError(false)
                }}/>
            {startDateTimeError && <p className={'error'}>Start date time are required</p>}
            <label htmlFor="endDateTime">End date time</label>
            <input type="text" id="endDateTime" value={endDateTime}
                   onChange={(evt) => setEndDateTime(evt.target.value)}/>

            <label htmlFor="stockPrices">Stock prices</label>
            <textarea id="stockPrices" placeholder="Separate with comma, e.g. 1, 2, 3"
                      style={{height: '200px'}}
                      value={stockPrices}
                      onChange={(evt) => {
                          setStockPrices(evt.target.value)
                          setStockPricesError(false)
                      }}/>
            {stockPricesError && <p className={'error'}>Stock prices are required</p>}
            <input type="submit" value="Submit"/>
        </form>
        {
            maxProfit &&
            <>
                <h2>Transaction details</h2>
                <div className={'max-profit-container'}>
                    <p><label className={'transaction-label'}>Max profit</label> <span className={'transaction-value'}>{maxProfit.maxProfit}</span></p>
                    <p><label className={'transaction-label'}>Buy date time</label> <span>{maxProfit.buyDateTime}</span></p>
                    <p><label className={'transaction-label'}>Sell date time</label> <span>{maxProfit.sellDateTime}</span></p>
                    <p><label className={'transaction-label'}>Buy price</label> <span>{maxProfit.buyPrice}</span></p>
                    <p><label className={'transaction-label'}>Sell price</label> <span>{maxProfit.sellPrice}</span></p>
                </div>
            </>
        }
    </div>

}

export default ProfitMaximizerPage
