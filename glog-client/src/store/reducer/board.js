const defaultURL = process.env.REACT_APP_SERVER_URL

const SET_POSTS = 'SET_POSTS'
const SET_DETAIL_POST = 'SET_DETAIL_POST'

const initialState = {
    posts: [],
    detailPost: [],
}

export const setPosts = (posts) => ({
    type: SET_POSTS,
    posts,
})

export const setDetailPost = (detailPost) => ({
    type: SET_DETAIL_POST,
    detailPost,
})

const getPostsByUserId = (pageNum) => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/post?pageNum=${pageNum}`, {
            method: 'GET',
            headers: {
                authorization: sessionStorage.getItem('key'),
                'X-USER-ID': sessionStorage.getItem('mail'),
            },
        })
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                console.log('getPostsByUserId data:: ', data)
                //dispatch(setPosts());
            })
    }
}

const getDetailPostAPI = (postIdx) => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/detail?idx=${postIdx}`, {
            method: 'GET',
        })
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                console.log('getDetailPostAIP :: ', data)
                //dispatch(setDetailPost());
            })
    }
}

const addPostAPI = (posts) => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/write`, {
            method: 'POST',
            header: {
                authorization: sessionStorage.getItem('key'),
                'X-USER-ID': sessionStorage.getItem('mail'),
            },
            body: JSON.stringify(posts),
        })
            .then((res) => {
                return res.JSON()
            })
            .then((data) => {
                console.log('addPostAPI : ', data)
                //dispatch(setDetailPost());
            })
    }
}

const changePostAPI = (postIdx, detailPost) => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/write`, {
            method: 'POST',
            header: {
                authorization: sessionStorage.getItem('key'),
                'X-USER-ID': sessionStorage.getItem('mail'),
            },
            body: JSON.stringify({
                postIdx: postIdx,
                contents: detailPost,
            }),
        })
            .then((res) => {
                return res.JSON()
            })
            .then((data) => {
                console.log('changePostAPI : ', data)
                //dispatch(setDetailPost());
            })
    }
}

const removePostAPI = (postIdx) => {
    return (dispatch, getState, { history }) => {
        fetch(`${defaultURL}/deletepost`, {
            headers: {
                authorization: sessionStorage.getItem('key'),
                'X-USER-ID': sessionStorage.getItem('mail'),
            },
            body: JSON.stringify({
                postIdx,
            }),
        })
            .then((res) => {
                return res.JSON()
            })
            .then((data) => {
                console.log('removePostAPI : ', data)
                // TODO: modal 띄우기
                //history.pop('/')
            })
    }
}

export default function board(state = initialState, action) {
    switch (action.type) {
        case SET_POSTS:
            return {
                ...state,
                posts: [...action.posts],
            }
        case SET_DETAIL_POST:
            return {
                ...state,
                detailPost: action.detailPost,
            }
        default:
            return state
    }
}

const actionCreator = {
    getPostsByUserId,
    getDetailPostAPI,
    addPostAPI,
    changePostAPI,
    removePostAPI,
}

export { actionCreator }
