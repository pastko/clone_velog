const defaultURL = process.env.REACT_APP_SERVER_URL
const SET_CONTENTS = 'SET_CONTENTS'
const SET_NEXT_CONTENTS = 'SET_NEXT_CONTENTS'

export const setContents = (contents) => ({
    type: SET_CONTENTS,
    contents,
})

export const setNextContents = (contents) => ({
    type: SET_NEXT_CONTENTS,
    contents,
})

const initialState = {
    contents: [],
}

const getContents = () => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/board?pageNum=0&sortRule=1`, {
            method: 'GET',
        })
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                console.log('getContents :: ', data.data)
                dispatch(setContents(data.data))
            })
    }
}

const getNextContents = () => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/board?pageNum=1&sortRule=1`, {
            method: 'GET',
        })
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                console.log('getContents :: ', data)
                dispatch(setNextContents(data))
            })
    }
}

export default function home(state = initialState, action) {
    switch (action.type) {
        case SET_CONTENTS:
            return {
                ...state,
                contents: action.contents,
            }
        case SET_NEXT_CONTENTS:
            return {
                ...state,
                contents: [...state.contents, ...action.contents],
            }
        default:
            return state
    }
}

const actionCreators = {
    getContents,
    getNextContents,
}

export { actionCreators }
