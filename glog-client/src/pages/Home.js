import React, { useEffect, useState } from 'react'
import InfiniteScroll from 'react-infinite-scroll-component'
import Post from '../components/main/Post'
import styled from 'styled-components'
import { actionCreators as homeActions } from '../store/reducer/home'
import { useDispatch, useSelector } from 'react-redux'

const Home = () => {
    const dispatch = useDispatch()
    const [noMore, setnoMore] = useState(true)
    const [page, setpage] = useState(0)

    useEffect(() => {
        dispatch(homeActions.getContents())
    }, [dispatch])

    let contents = useSelector((state) => state.home.contents.data)

    console.log('contents :: ' + contents)

    // const fetchData = async () => {
    //     const postsFormServer = await fetchComments()
    //     setPosts([...contents, ...postsFormServer])

    //     if (postsFormServer.length === 0 || postsFormServer.length < 10) {
    //         setnoMore(false)
    //     }
    //     setpage(page + 1)
    // }

    return (
        <>
            <StyleInfiniteScroll>
                <InfiniteScroll
                // dataLength={contents.length} //This is important field to render the next data
                // // next={fetchData}
                // hasMore={noMore}
                >
                    {contents.length === 0
                        ? 'Emtpy'
                        : contents.map((post) => {
                              return <Post key={post.idx} post={post} />
                          })}
                </InfiniteScroll>
            </StyleInfiniteScroll>
        </>
    )
}

const StyleInfiniteScroll = styled.div`
    max-width: 1280px;
    margin: 20px auto;
    position: flex;
    transform: translate(9.5%);
`

export default Home
