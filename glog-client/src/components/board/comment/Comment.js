import styled from 'styled-components'
import CommentItems from './CommentItems'
import CommentWrite from './CommentWrite'
import { useCallback, useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators as commentActions } from '../../../store/reducer/comments'

function Comment(props) {
  const [comment, setComment] = useState('')
  const onChagneComment = useCallback((e) => {
    setComment(e.target.value)
  }, [])

  const isLogin = useSelector((state) => state.user.is_login)
  const user = useSelector((state) => state.user.user)
  const postId = props.postId
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(commentActions.setCommentAPI(postId))
  }, [dispatch, postId])

  const goSetComment = (e) => {
    if (!isLogin) return alert('로그인이 필요한 서비스입니다.')

    const data = {
      user: user,
      contets: comment,
      postId: postId,
    }

    dispatch(commentActions.addCommentAPI(data))
  }
  return (
    <SytledComment>
      <h4>0개의 댓글</h4>
      <CommentWrite
        value={comment}
        type="write"
        _onChange={onChagneComment}
        _onClick={goSetComment}
      />
      <StyledCommmentList>
        {/* {
                    comments.map((com) => {
                        return <CommentItems />;
                    })
                } */}
        <CommentItems />
        <CommentItems />
        <CommentItems />
      </StyledCommmentList>
    </SytledComment>
  )
}

const SytledComment = styled.div`
  text-align: left;
  h4 {
    font-size: 1.125rem;
    line-height: 1.5;
    font-weight: 600;
    margin-bottom: 1rem;
  }
`

const StyledCommmentList = styled.div`
  margin-top: 2rem;
`

export default Comment
